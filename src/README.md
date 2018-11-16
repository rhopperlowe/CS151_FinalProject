# CS151_FinalProject
Works by implementing Jcomponent. The scene class handles all the game drawing. GameTester will add and remove stuff from the screen as needed. Need to decide if logic is going to be handeled in Scene. It would be better if the logic was done outside of the sceneclass in GameTester. To do this we will most likely need to implment an Observer Model


## Requirements
- Collision Detection (Seb)
- random enemy generation (Seb)
- random enemy positioning (Seb)
- MVC for game tester and scene component, timer, lives (Ryan)
	- StatusBar class extends JPanel
- Main method has no more than 2-3 lines of code
	- GameInstance class extends JFrame handles all game logic
- game logic:
	- two lives (Ezana)
	- 1:30 min timer (Ezana)
	- remove enemies and paint new ones on exit/entry (Seb)
	