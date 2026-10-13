# 🎬 Java Cinema Ticket Booking System

A robust, object-oriented Java console application developed to manage movie ticket bookings for cinema transactions. The application enforces data encapsulation, rigorous user input validation boundaries, transactional cost logic calculations, volume-based discount matrices, and record management filters using dynamic collection structures.

---

## ✨ Implemented Core Features

* **Data Encapsulation Blueprint:** Tracks object attributes including unique Booking IDs, customer names, movie titles, type variants, ticket counts, and total balances using private fields.
* **Format-Validated Input Streams:** Enforces validation rules ensuring non-empty ID tags, non-empty customer tracking names, positive ticket count integers (>0), and valid category option parameters.
* **Pricing Variant Matrix:** Implements switch-case pricing mapping routines across distinct category tiers matching South African Rand pricing:
  * Standard: R50.00
  * 3D: R70.00
  * IMAX: R100.00
  * VIP: R150.00
* **Conditional Volume Discounts:** Computes specific promotional tier reductions using multi-conditional if-else evaluation structures:
  * 1–3 tickets: No discount tier applied (Standard Rate).
  * 4–5 tickets: 10% structural balance reduction deduction applied.
  * 6 or more tickets: 15% maximum volume bracket reduction applied.
* **Dynamic Record Administration:** Performs sequential loop array checking operations to support displaying full logs, searching specific items by ID key tracking strings, aggregating matches by movie keyword, and executing clean object deletions out of the database array storage layout space.

---

## 🛠️ Project Source Architecture

* **`Booking.java`**: The core blueprint design template class containing constructor configurations and internal parameter attributes.
* **`CinemaBookingSystem.java`**: The main interface driver orchestrator managing system scanner loops, option selections, error handlers, and transactional execution flows.

---

## 🚀 Execution Instructions

1. Open the project folder structure directly inside **Apache NetBeans IDE**.
2. Locate the **`CinemaBookingSystem.java`** master driver execution file tab.
3. Right-click anywhere within the empty text editor code canvas space and select **Run File** (or press **Shift + F6** on your keyboard layout).
4. Follow the interactive terminal selections menu prompt loops (options 1-8) to verify operational features or terminate the runtime session gracefully.
