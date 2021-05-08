package ca.senecacollege.ConnectFour;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConnectFour extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		Pane pane = new Pane();
		//all circles of the game
		Circle circle[] = new Circle[42];
		int index = 0;
		//set the radius, position of circles and append to the pane
		for(int i = 0, j = 1; i < 7; i++, j+=2) {
			for(int k = 0, t = 1; k < 6; k++, t+=2) {
				circle[index] = new Circle();
				circle[index].setRadius(50);
				circle[index].setCenterX(50 * j);
				circle[index].setCenterY(50 * t);
				circle[index].setStroke(Color.BLACK);
				circle[index].setFill(Color.WHITE);
				pane.getChildren().addAll(circle[index++]);
			}
		}
		
		//the player's turn variable
		boolean[] yr = {true};
		
		//on click event
		EventHandler<MouseEvent> onClick = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				Circle cir = (Circle) e.getSource();
				
				//get the circle number from the circle[] array
				String player = null;
				int x = (int) ((cir.getCenterX() - 50) / 100);
				int y = (int) ((cir.getCenterY() - 50) / 100);
				int index, pos = x * 6 + y;
				//get the column
				if(pos < 6)
					index = 5;
				else if(pos < 12)
					index = 11;
				else if(pos < 18)
					index = 17;
				else if(pos < 24)
					index = 23;
				else if(pos < 30)
					index = 29;
				else if(pos < 36)
					index = 35;
				else
					index = 41;
				
				//fill the color as per player
				for(int i = index; i >= index - 5; i--) {
					if(circle[i].getFill() == Color.WHITE) {
						if(yr[0]) {
							circle[i].setFill(Color.YELLOW);
							player = "Yellow";								
						}
						else {
							circle[i].setFill(Color.RED);
							player = "Red";
						}
						pos = i;
						yr[0] = !yr[0];
						break;
					}
				}
				//four consecutive circles
				String winner = null;
				Paint c[] = new Paint[4];
				
				//set the first color
				if(player == "Red")
					c[0] = Color.RED;
				else
					c[0] = Color.YELLOW;
				
				//check the winner
				
				//check the right side
				for(int i = 1; i < 4; i++) {
					int nextInt = pos + (i * 6);
					if(nextInt >= 0 && nextInt < 42)
						c[i] = circle[nextInt].getFill();
					else {
						c[1] = null; c[2] = null; c[3] = null;
						break;
					}
				}
				if(c[0] == c[1] && c[0] == c[2] && c[0] == c[3])
					winner = player;
				
				//check the left side
				for(int i = 1; i < 4; i++) {
					int nextInt = pos - (i * 6);
					if(nextInt >= 0 && nextInt < 42)
						c[i] = circle[nextInt].getFill();
					else {
						c[1] = null; c[2] = null; c[3] = null;
						break;
					}
				}
				if(c[0] == c[1] && c[0] == c[2] && c[0] == c[3])
					winner = player;
				
				//check the top
				for(int i = 1; i < 4; i++) {
					int nextInt = pos - 1*i;
					if(nextInt >= 0 && nextInt < 42)
						c[i] = circle[nextInt].getFill();
					else {
						c[1] = null; c[2] = null; c[3] = null;
						break;
					}
				}
				if(c[0] == c[1] && c[0] == c[2] && c[0] == c[3])
					winner = player;
				
				//check the down
				for(int i = 1; i < 4; i++) {
					int nextInt = pos + 1*i;
					if(nextInt >= 0 && nextInt < 42)
						c[i] = circle[nextInt].getFill();
					else {
						c[1] = null; c[2] = null; c[3] = null;
						break;
					}
				}
				if(c[0] == c[1] && c[0] == c[2] && c[0] == c[3])
					winner = player;
				
				//check the cross right up
				for(int i = 1; i < 4; i++) {
					int nextInt = pos + (i * 5);
					if(nextInt >= 0 && nextInt < 42)
						c[i] = circle[nextInt].getFill();
					else {
						c[1] = null; c[2] = null; c[3] = null;
						break;
					}
				}
				if(c[0] == c[1] && c[0] == c[2] && c[0] == c[3])
					winner = player;
				
				//check the cross right down
				for(int i = 1; i < 4; i++) {
					int nextInt = pos + (i * 7);
					if(nextInt >= 0 && nextInt < 42)
						c[i] = circle[nextInt].getFill();
					else {
						c[1] = null; c[2] = null; c[3] = null;
						break;
					}
				}
				if(c[0] == c[1] && c[0] == c[2] && c[0] == c[3])
					winner = player;
					
				//check the cross left down
				for(int i = 1; i < 4; i++) {
					int nextInt = pos - (i * 5);
					if(nextInt >= 0 && nextInt < 42)
						c[i] = circle[nextInt].getFill();
					else {
						c[1] = null; c[2] = null; c[3] = null;
						break;
					}
				}
				if(c[0] == c[1] && c[0] == c[2] && c[0] == c[3])
					winner = player;
				
				//check the cross right down
				for(int i = 1; i < 4; i++) {
					int nextInt = pos - (i * 7);
					if(nextInt >= 0 && nextInt < 42)
						c[i] = circle[nextInt].getFill();
					else {
						c[1] = null; c[2] = null; c[3] = null;
						break;
					}
				}
				if(c[0] == c[1] && c[0] == c[2] && c[0] == c[3])
					winner = player;
			
				
				if(winner != null) {
					Stage winnerStage = new Stage();
					
					//disable the previous stage
					winnerStage.initOwner(primaryStage);
					winnerStage.initModality(Modality.WINDOW_MODAL);
					GridPane pane1 = new GridPane();
					Label l1 = new Label(winner + " Player won the game!!");
					l1.setTextFill(Color.WHITE);
					pane1.add(l1, 2, 2);
					pane1.setStyle("-fx-background-color: " + winner);
					Scene scene2 = new Scene(pane1, 300, 100);
					winnerStage.setTitle("Game Over!!");
					winnerStage.setScene(scene2);
					winnerStage.show();
				}
			}
		};
		
		
		//game logic
		for(int i = 0; i < circle.length; i++) {
			//for()
			circle[i].setOnMousePressed(onClick);
		}
		
		//on click event
		
		Scene scene = new Scene(pane, 700, 600, Color.BLUE);
		primaryStage.setTitle("Connect Four");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void set() {
		
	}

	//main method
	public static void main(String[] args) {
		launch(args);
	}
}
