package zpbd.paxos.actors

import akka.actor.{Actor, ActorLogging, ActorRef}
import zpbd.paxos.Paxos.Phase.{Accept, Idle, Prepare}
import zpbd.paxos.Paxos._

import scala.collection.mutable

/**
 * Created by gospo on 06.04.15.
 */
class Proposer(id:Int, acceptors: Set[ActorRef]) extends Actor with ActorLogging {

  var nextN = 0
  var curN: Int = 0
  var curV: Any = null
  var accepted: mutable.Set[ActorRef] = mutable.Set[ActorRef]()
  var curPhase = Idle
  var highestNProposal: Option[Proposal] = None

  override def receive: Receive = {
    case Init(v) =>
      log.info(s"Init with v: $v")
      curN = nextN; nextN += 1; curV = v; accepted.clear(); curPhase = Prepare

      acceptors foreach {_ ! PrepareRequest(Proposal(curN, v))}

    case PrepareResponse(n, proposal) =>
      log.info(s"Prepare response with n: $n proposal: $proposal")
      if(curPhase == Prepare && curN == n) {
        accepted.add(sender)
        (proposal, highestNProposal) match {
          case (Some(Proposal(n, v)), Some(Proposal(hn, hv))) if n > hn => highestNProposal = proposal
          case (proposal, None) => highestNProposal = proposal
        }
        if (accepted.size > acceptors.size / 2) {
          log.info(s"Majority of acceptors has responded to prepare request with n: $curN.")
          curPhase = Accept
          val v = if(highestNProposal.isDefined) highestNProposal.get.v else curV
          accepted foreach {_ ! AcceptRequest(Proposal(curN, v))}
        }
      }

    case PrepareRejection(n) =>
      log.info(s"Prepare rejection with n: $n")
      nextN = n + 1
      curPhase = Idle

  }
}