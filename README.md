# 🐉 Ice and Fire: Dragon Only

A dedicated, streamlined, and optimized fork of **Ice and Fire (1.20.1)** focusing purely on **Dragons and Dragon Content**.

---

## ✨ Features

- **🐲 Dragons Only:** Kept Fire, Ice, and Lightning dragons along with their respective nests, roosts, caverns, dragon scales, tools, weapons, and armor. Removed unrelated mythical creatures and structures (Gorgons, Sirens, Hippogryphs, Pixies, Cyclopes, Death Worms, Stymphalian Birds, Cockatrices, Myrmex, etc.) for peak performance and clean world generation.
- **⚡ Built-in Citadel:** Citadel is bundled directly inside the mod JAR using Forge's Jar-in-Jar system. No separate Citadel mod download needed!
- **🧠 Integrated Dragon AI Fixes:** Integrated dragon flight, navigation, and combat AI improvements directly from `iaf_dragons` (by Kettle5000).
- **🔍 Native Jade Tooltip Support:** Looking at any dragon (or dragon hitbox part) displays:
  - Gender: ♂ Male / ♀ Female
  - Stage & Age in days (e.g., `Stage: 3 (62 Days)`)
  - Taming & Owner status
- **⚖️ Balanced Loot & Ore Progression:** Removed redundant silver and copper tools, armor, and ores. Dragon roosts now feature vanilla-compatible Iron Piles that drop iron nuggets.

---

## 🛠️ Building From Source

Requirements:
- JDK 17 (e.g. Eclipse Adoptium Temurin 17)

Run the following command in the project root:
```bash
./gradlew build -x test
```
The compiled mod JAR will be located in `build/libs/`.

---

## 📜 Credits & License

This project is licensed under the **GNU Lesser General Public License v3.0 (LGPL-3.0)**.

- **Original Ice and Fire:** Created by [Alexthe666](https://github.com/Alex-the-666/Ice_and_Fire) and contributors.
- **Citadel Library:** Created by [Alexthe666 / BobMowzie](https://github.com/alexthe666/citadel).
- **Dragon AI Fixes:** Created by [Kettle5000](https://github.com/Kettle5000/IceAndFire-Dragon-AI-Fixes).
