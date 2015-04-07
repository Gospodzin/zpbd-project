package zpbd.paxos

/**
 * Created by gospo on 06.04.15.
 */
object Paxos {
  case class Proposal(n: Int, v: Any)

  // auxiliary message
  case class Init(v: Any)

  case class PrepareRequest(proposal: Proposal)
  case class PrepareResponse(n: Int, proposal: Option[Proposal])
  case class PrepareRejection(n: Int)

  case class AcceptRequest(proposal: Proposal)
  case class AcceptRejection(n: Int)

  object Phase extends Enumeration {
    type Phase = Value
    val Prepare, Accept, Idle = Value
  }
}
