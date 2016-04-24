package chessdesktop;

import Chess.ChessPiece;
import Chess.ChessBoardImplementation;
import Chess.PiecePosition;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FXMLDocumentController implements Initializable {
	
	ChessBoardRenderer board = new ChessBoardRenderer();

	@FXML
	private Label label;
	@FXML
	private Canvas canvas;
	
	@FXML
	private void handleButtonAction(ActionEvent event) {
		board = new ChessBoardRenderer();
		board.draw(canvas);
	}
	
	@FXML
	private void handleSaveButtonAction(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Game");
		File file = fileChooser.showSaveDialog(null);
		if (file != null) {
			Charset charset = Charset.forName("US-ASCII");
                        String s = file.toPath().toString();
			try (BufferedWriter writer = Files.newBufferedWriter(file.toPath(), charset)) {                            
                                ChessBoardImplementation a = new ChessBoardImplementation();
                                ChessPiece[] pieces = a.getPieces();
                                PiecePosition b;
                                for (ChessPiece piece : pieces) {
                                    if (piece != null) {
                                        b = a.getPiecePosition(piece);
                                        writer.write(piece.getColor()+","+piece.getType()+","+
                                                b.getRow() + "," + b.getColumn());   

                                        writer.write("\n");
                                    }
                                }
			} 
			catch (IOException x) {
				System.err.format("IOException: %s%n", x);
			}
		}
	}

	@FXML
        @SuppressWarnings("empty-statement")
	private void handleLoadButtonAction(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Chess Files", "*.xml"));
		File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {
                    
                    //Falta Borrar con canvas
                    
                for(int row=0;row<8;row++) {
                    for(int column=0;column<8;column++) {
                        pieces[getPieceIndex(row, column)] = null;
                   }   
                }
                    
                    //Detectar y dibujar con canvas
			try {
				Scanner in = new Scanner(selectedFile);
                                while(in.hasNext()) {
                                    String line = in.next();
                                    if (line != null) {
                                        line.split(",");
                                        
               
               // Lectura del fichero
               String linea;
               while((linea=br.readLine())!=null) {
                   String[] campos = linea.split(",");
                   int row,column;
                   row = Integer.parseInt(campos[2]);
                   column = Integer.parseInt(campos[3]);
                   ChessPiece.Color col = ChessPiece.Color.valueOf(campos[0]);
                   ChessPiece.Type tip = ChessPiece.Type.valueOf(campos[1]);
                   pieces[getPieceIndex(row, column)] = new ChessPieceImplementation(col,tip);
               }
                                    }
                                }
                                
                                
                                ChessBoardImplementation a = new ChessBoardImplementation();
                                a.loadFromFile(selectedFile);
                                
			} catch (IOException ex) {
				Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
			}
			board.draw(canvas);
		}
	}
        
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		board.draw(canvas);
		
		canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e)->{
			Chess.ChessPiece piece = board.getPieceAt(canvas, e.getX(), e.getY());
			if (board.getMovingPiece() == piece) {
				board.setMovingPiece(null);
				board.draw(canvas);
				return;
			}
			if (board.getMovingPiece() == null) {
				board.setMovingPiece(piece);
				board.draw(canvas);
				return;
			}
			if (board.movePieceTo(canvas, e.getX(), e.getY())) {
				board.setMovingPiece(null);
				board.draw(canvas);
				if (!board.containsKing(ChessPiece.Color.BLACK) || !board.containsKing(ChessPiece.Color.WHITE)) {
					if (!board.containsKing(ChessPiece.Color.BLACK))
						label.setText("Ganan las blancas");
					else
						label.setText("Ganan las negras");
				}
			}
		});
	}	
	
}
