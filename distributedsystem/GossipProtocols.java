package distributedsystem;

//  Gossip Protocols (Epidemic Protocols):
//  A gossip protocol is a decentralized communication mechanism where nodes periodically exchange information
//  with a small, random subset of peers — similar to how rumors spread in social networks.
//  Goal: Spread information eventually to all nodes without coordination or leaders.

//  Why Gossip Exists: In large distributed systems,
//  Central coordination does not scale
//  Nodes frequently join/leave
//  Failures are common
//  Network topology is dynamic

//  Core Properties of Gossip Protocols:
//  Decentralized (no leader)
//  Eventually consistent
//  Fault tolerant
//  Highly scalable

//  How Gossip Works (Basic Mechanism): Each node periodically:
//  Picks k random peers, Exchanges state information, Merges received data, Repeats.
//  Over time: Information spreads exponentially, All healthy nodes converge.


//  Gossip Communication Styles:
//  1. Push Gossip: Node sends updates to peers
//  2. Pull Gossip: Node asks peers for updates
//  3. Push-Pull (Most Common): Exchange state in both directions
//  Push-pull converges fastest.

public class GossipProtocols {


}
