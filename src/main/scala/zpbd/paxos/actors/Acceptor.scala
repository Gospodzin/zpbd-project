package zpbd.paxos.actors

import akka.actor.{Actor, ActorLogging, Props}
import zpbd.paxos.Paxos._

/**
 * Created by gospo on 06.04.15.
 */
class Acceptor extends Actor with ActorLogging {

  var acceptedProposal: Option[Proposal] = None

  override def receive: Receive = {
    case PrepareRequest(requestProposal) =>
      log.info(s"Prepare request with proposal: $requestProposal")
      acceptedProposal match {
        case Some(p) if p.n >= requestProposal.n => // already accepted a proposal with greater n
          sender ! PrepareRejection(p.n)
        case _ =>
          sender ! PrepareResponse(requestProposal.n, acceptedProposal)
      }

    case AcceptRequest(requestProposal) =>
      log.info(s"Accept request with proposal: $requestProposal")
      acceptedProposal match {
        case Some(p) if p.n >= requestProposal.n => // accepted a proposal with greater n
          sender ! AcceptRejection(requestProposal.n)
        case _ =>
          log.info(s"$requestProposal has been accepted")
      }
  }
}

object Acceptor {
  val props = Props[Acceptor]
}