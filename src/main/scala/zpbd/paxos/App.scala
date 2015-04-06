package zpbd.paxos

import akka.actor.{ActorSystem, Props}
import zpbd.paxos.Paxos.Init
import zpbd.paxos.actors.{Acceptor, Proposer}

/**
 * Created by gospo on 06.04.15.
 */
object App extends App{
  val system = ActorSystem("MyActorSystem")
  val proposer = system.actorOf(Proposer.props, "proposer")
  //val learner = system.actorOf(Learner.props, "learner")
  val acceptors = for (i <- 0 until 10) yield system.actorOf(Props[Acceptor], name = s"acceptor-$i")

  proposer ! Init("Hello")

  system.awaitTermination()
}
