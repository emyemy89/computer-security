
# Lab Report: TCP/IP Attacks

## Network Setup Summary

- **10.0.20.3 – Attacker**  
  Sends malicious traffic to test or exploit the internal host.

- **10.0.20.2 – Internal Host**  
  Simulates a regular internal machine; target of attacks.

- **10.0.20.1 – Firewall (Internal Interface)**  
  Filters traffic between the internal network and firewall.

- **10.0.10.1 – Firewall (External Interface)**  
  Filters traffic between the firewall and the external network.

- **10.0.10.2 – Outside Host**  
  Represents a safe external system; used for testing normal external access.

---

## Task 1 – Network Traffic Inspection and Telnet Hijacking

In this task, packets captured during a Telnet session were inspected to understand how credentials are transmitted without encryption. A TCP session hijacking attack was then performed using packet injection techniques.

### Task Summary

| Task | Description |
|------|-------------|
| **1.1** | **Inspect Telnet packet capture:** The username and password were extracted by analyzing a `.pcap` file using Wireshark. |
| **1.2** | **TCP Session Hijacking:** A Telnet session was hijacked by spoofing TCP packets with the correct sequence number, using `tcpdump` and `hping3` to inject arbitrary commands. |

---

## Task 2 – Attacks on TCP/IP Protocol Stack

This task involved performing attacks at various layers of the TCP/IP protocol stack. The lab environment was set up by launching a virtual machine, starting the necessary containers (`attacker`, `inside-host`, `outside-host`, and `firewall`), and connecting to each container via separate terminals.

### Task Summary

| Task | Description |
|------|-------------|
| **2.1** | **ARP Cache Poisoning:** The tool `netwox 33` was used to spoof ARP replies, redirecting traffic meant for the gateway (firewall) to the attacker. The impact was verified by observing disruptions in a continuous ping. |
| **2.2** | **ICMP Redirect Attack:** The tool `netwox 86` was used to send forged ICMP redirect messages, altering the routing of packets from `inside-host` to the attacker. This was combined with ARP spoofing to amplify the effect. |
| **2.3** | **TCP Session Hijacking + Reverse Shell:** A Telnet session was hijacked and a command was injected to establish a reverse shell. This provided the attacker with full shell access to the victim. |

