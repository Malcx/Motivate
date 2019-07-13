import javafx.application.Application;


import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Random;
import java.util.Properties;

public class Motivate extends Application {

	@Override
	public void start(Stage stage) {
 
		Properties props = new Properties();
	 	String theMessage = "Focus";
		int displayTime = 1500 + (int)(Math.random()*1500); // 1.5 to 3 second default
		try{
 
 
 			InputStream propInput = Motivate.class.getClassLoader().getResourceAsStream("config.props");
			props.load(propInput);
  			displayTime = Integer.parseInt(props.getProperty("displayTime"));
 
			InputStream input = Motivate.class.getClassLoader().getResourceAsStream("messages.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			ArrayList<String> array = new ArrayList<>();
			String line;
			while((line = br.readLine()) != null)
				array.add(line);
			// variable so that it is not re-seeded every call.
			Random rand = new Random();

			// nextInt is exclusive. Should be good with output for array.
			int randomIndex = rand.nextInt(array.size());

			// Print your random quote... 
			theMessage = array.get(randomIndex);
			theMessage = theMessage.replace("|", "\n");
			System.out.println(array.get(randomIndex));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


		// https://stackoverflow.com/questions/32355335/on-javafx-how-to-hide-stage-without-disposing-it-and-closing-the-application
		// stop exit when hide window
		// Platform.setImplicitExit(false);
 
		// StageStyle.UTILITY means no taskbar, but cannot be TRANSPARENT

		int screenCount = 0;
		for (Screen screen : Screen.getScreens()) 
			screenCount++;

		Stage[] stageArray = new Stage[screenCount];
		StackPane[] stackPaneArray = new StackPane[screenCount];
		
		
		int r = 2 + (int)(Math.random()*7);
		int g = 2 + (int)(Math.random()*7);
		int b = 2 + (int)(Math.random()*7);

		int thisScreenID = 0;
		for (Screen screen : Screen.getScreens()) 
            	{
	            	stageArray[thisScreenID] =  new Stage(StageStyle.UTILITY);
            		Rectangle2D bounds = screen.getVisualBounds();
            	        stageArray[thisScreenID].setX(bounds.getMinX() + 100);
            	 	stageArray[thisScreenID].setMaximized(true);
			stageArray[thisScreenID].setAlwaysOnTop(true);
			
			stackPaneArray[thisScreenID] = new StackPane();
			stackPaneArray[thisScreenID].getStylesheets().add("application.css");
			
			stageArray[thisScreenID].setScene(new Scene(stackPaneArray[thisScreenID], 300, 250));
			
			stackPaneArray[thisScreenID].setStyle("-fx-background-color: #" + r + g + b);

			Label label = new Label(theMessage);
			stackPaneArray[thisScreenID].getChildren().add(label);
			
			stageArray[thisScreenID].show();
			
			thisScreenID++;

            	}

		stageArray[0].setFullScreen(true);
		stageArray[0].setFullScreenExitHint("");




		try{
			Thread.sleep(displayTime);
		}
		catch(Exception e)
		{}
		
		for(int i=0;i<screenCount;i++)
			stageArray[i].hide();
		System.exit(0);
	}

	public static void main(String[] args) {
		launch(args);
	}

}