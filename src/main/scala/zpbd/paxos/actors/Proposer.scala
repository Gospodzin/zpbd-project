package zpbd.paxos.actors

import akka.actor.{Actor, Props}

/**
 * Created by gospo on 06.04.15.
 */
class Proposer extends Actor {
  override def receive: Receive = ???
}

object Proposer {
  val props = Props[Proposer]
}