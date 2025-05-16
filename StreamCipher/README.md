# Stream Cipher 

This directory contains implementations of stream ciphers and pseudo-random number generators in Java, developed as part of a university assignment. The project involves building a simple stream cipher, designing a custom PRNG, and implementing the RC4 algorithm from scratch.

---

## Task 1: Simple Synchronous Stream Cipher

A basic stream cipher was implemented using Java’s built-in pseudo-random number generator (`java.util.Random`). Encryption and decryption are performed by XOR-ing each input byte with a pseudo-random byte, generated using a shared key as the PRNG seed.

- **PRNG**: `java.util.Random`
- **Key usage**: Provided as the seed for the PRNG
- **Random bytes**: Generated via `nextInt(256)`
- **Operation**: Byte-wise XOR for both encryption and decryption

📄 File: `StreamCipher.java`

---

## Task 2: Custom Pseudorandom Number Generator

A custom PRNG named `MyRandom` was implemented by subclassing Java’s `Random` class. The design is based on the Linear Congruential Generator (LCG) model, with manually chosen constants and modulus values.

- Methods `next(int bits)` and `setSeed(long seed)` were overridden
- Compatibility with Java’s standard `Random` interface was maintained
- The `StreamCipher` class from Task 1 was reused, replacing the PRNG with `MyRandom`

A short report (`Report.pdf`) includes a basic analysis of the PRNG’s randomness characteristics, including distribution and repeatability evaluations.

📄 Files:
- `MyRandom.java`
- `StreamCipher.java` (with `MyRandom`)
- `Report.pdf`

---

## Task 3: RC4 from Scratch

The RC4 stream cipher was implemented manually, without relying on existing libraries. The implementation generates 8-bit outputs using a `next(8)` method and supports keys of variable lengths using `BigInteger`.

- **Interface**: Only `next(8)` is required
- **Key handling**: Keys are represented as `BigInteger` values
- **Key size**: Determined by the byte length of the key’s value
- **Compatibility**: Designed to match the output of standard RC4 implementations

The stream cipher logic was integrated into the existing `StreamCipher` structure, ensuring modularity and consistency across tasks.

📄 File: `StreamCipher.java` (with RC4 logic)

---

## Summary

This project demonstrates the implementation of stream ciphers using both standard and custom pseudo-random number generators in Java. RC4 was recreated to comply with reference implementations, and a basic custom PRNG was evaluated for use in cipher applications.

| Task | Description | Files |
|------|-------------|-------|
| 1 | Stream cipher using Java's built-in PRNG | `StreamCipher.java` |
| 2 | Custom LCG-based PRNG with stream cipher | `MyRandom.java`, `StreamCipher.java`, `Report.pdf` |
| 3 | Full RC4 implementation in Java | `StreamCipher.java` |

---


``
