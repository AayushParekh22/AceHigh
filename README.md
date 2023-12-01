# AceHigh: Blackjack Simulation Model

## Overview
AceHigh is a streamlined simulation model for the classic casino game Blackjack. With a focus on the core gameplay, AceHigh provides a simplified yet engaging Blackjack experience against a virtual dealer. Although features like splitting pairs and insurance bets are omitted, AceHigh still delivers the thrill of the hit or stay decision-making that is quintessential to Blackjack.

## Features
- **Simplified Blackjack Rules**: Focus on the fundamental aspects of Blackjack, making it accessible for beginners and a quick play for seasoned players.
- **Natural Blackjack Detection**: Identifies and rewards the special 'Natural Blackjack' hand appropriately.
- **Automated Dealer Logic**: Dealer actions are automated based on traditional Blackjack rules, ensuring a fair and consistent game.
- **Custom Betting System**: Allows bets using virtual chips with different denominations.
- **Graphical User Interface (GUI)**: Although the model is separate, a GUI is provided for visual interaction with the game.

## Cards and Hands
- A standard deck of 52 playing cards is used.
- Special attention is given to the hand values in Blackjack, with specific rules for calculating the value of hands containing Aces.
- A 'Natural Blackjack' is defined as an Ace paired with a 10-value card, which awards the player with 1.5 times their bet unless the dealer also has a Natural Blackjack, resulting in a push.

## Rules of Blackjack in AceHigh
- **Betting**: Place your virtual bet before the hand is dealt.
- **Initial Cards**: Both the player and the dealer receive two cards, with one of the dealer's cards being face-down.
- **Player's Turn**: Choose to 'hit' for more cards or 'stay' to end your turn without exceeding a total of 21.
- **Dealer's Turn**: The dealer reveals the hidden card and follows set rules to decide whether to hit or stay.
- **Outcome Assessment**: Determine win, loss, or push based on the final hands of the player and dealer.

## Getting Started
- Download the project files and import them into your preferred IDE.
- Compile and run the provided JUnit tests to ensure the logic of the model is functioning as intended.
- For a visual experience, run the `BlackjackGUI` class after implementing the model.

## Implementation Requirements
- **Deck Class**: Implement a deck of cards that can be shuffled and dealt.
- **BlackjackModel Class**: This is where the game logic resides, managing hands, assessing outcomes, and directing the dealer's actions.
- **Testing**: A suite of JUnit tests is included to validate the functionality of your implementation.

## Running the Game
Once you have implemented the `Deck` class and the `BlackjackModel` class, you can interact with the game through the GUI. Run the main method in the `GUI/BlackjackGUI` class for a full gaming experience.

[![Video Thumbnail](https://example.com/video_thumbnail.jpg)](https://vimeo.com/yourusername/videos/780842514)

## Contributions
While the model is robust, contributions to expand its features or improve the codebase are welcome. Potential enhancements include implementing additional Blackjack actions, refining the betting system, or optimizing the dealer logic.

## Feedback
Any feedback or suggestions for AceHigh can be submitted as issues in the GitHub repository.

## License
AceHigh is open-sourced under the MIT License. Feel free to use, modify, and distribute the code as part of your projects.

---

Get ready to challenge the dealer and refine your Blackjack strategy with AceHigh!
