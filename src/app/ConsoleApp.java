package app;

import java.util.Scanner;

import app.commands.Command;
import app.commands.CommandFactory;
import app.commands.CreateCommand;
import app.commands.DrawModelCommand;
import app.commands.QuitCommand;
import app.exception.InvalidCommandException;
import app.exception.InvalidCommandParams;
import app.exception.InvalidModelException;
import app.models.Canvas;
import app.models.CanvasImpl;
import app.models.ModelFactory;

public class ConsoleApp {
	
	 private static Canvas         canvas;
	 private static Scanner        scanner;
	 private static CommandFactory commandFactory;
	 private static ModelFactory  modelFactory;

	public static void main(String[] args) {
		
		scanner = new Scanner(System.in);
        commandFactory = new CommandFactory();
        modelFactory = new ModelFactory();
        
        System.out.println("Welcome to Canvas Console App ver.1.0.0 ");
        System.out.println("This App has been created by Igor Radovanovic");
        System.out.println("=============================================");
        System.out.println("Enter command:");

        while (true) {
            process(scanner.nextLine());
            
            System.out.println("Enter command:");
        }

	}
	
	 private static void process(String commandLine) {
	        Command command = null;

	        try {
	            command = commandFactory.getCommand(commandLine);
	        } catch (InvalidCommandException e) {
	            System.out.println("Invalid command, please enter a valid command.");
	        } catch (InvalidCommandParams invalidCommandParams) {
	            System.out.println("Command not ok. Command syntax is not correct: " + invalidCommandParams.getMessage());
	            System.out.println("Use this help to correct syntax: \n" + invalidCommandParams.getHelpMessage());
	        }

	        if (command instanceof QuitCommand) {
	            quit();
	        }

	        if (command instanceof CreateCommand) {
	            createNewCanvas((CreateCommand) command);
	            return;
	        }

	        if (command instanceof DrawModelCommand) {
	            draw((DrawModelCommand) command);
	        }
	    }
	  private static void createNewCanvas(CreateCommand command) {
	        canvas = new CanvasImpl(command.getWidth(), command.getHeight());
	        System.out.println(canvas.render());
	    }

	  private static void quit() {
	        scanner.close();
	        System.out.println("Thank you for using this App. Exit.......");
	        System.exit(0);
	    }
	    
	  private static void draw(DrawModelCommand command) {
	        if (canvas == null) {
	            System.out.println("Error. You need to create a canvas first");
	            return;
	        }
	        try {
	            canvas.addModel(modelFactory.getModel(command));
	            System.out.println(canvas.render());
	        } catch (InvalidModelException ex) {
	            System.out.println("Error. Can not add the model to canvas: " + ex.getMessage());
	        }
	    }

}
