package zpbd.paxos.actors

import akka.actor.{Actor, Props}

/**
 * Created by gospo on 06.04.15.
 */
class Acceptor extends Actor {
  override def receive: Receive = ???
}

object Acceptor {
  val props = Props[Acceptor]
}