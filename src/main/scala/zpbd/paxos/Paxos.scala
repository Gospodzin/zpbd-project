package zpbd.paxos

/**
 * Created by gospo on 06.04.15.
 */
object Paxos {
  class Proposal(n: Int, v: Any)

  // auxiliary message
  case class Init(v: Any)

  case class PrepareRequest(proposal: Proposal)
  case class PrepareResponse(proposal: Proposal)

  case class AcceptRequest(proposal: Proposal)
}
