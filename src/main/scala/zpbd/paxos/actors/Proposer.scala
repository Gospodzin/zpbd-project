package zpbd.paxos.actors

import akka.actor.{Actor, Props}
import zpbd.paxos.Paxos.Init

/**
 * Created by gospo on 06.04.15.
 */
class Proposer extends Actor {
  override def receive: Receive = {
    case Init(v: Any) =>
      println(v)
      context.system.shutdown()
  }
}

object Proposer {
  val props = Props[Proposer]
}