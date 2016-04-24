package Chess;

public class ChessPieceImplementation implements ChessPiece {
    
    /**
     * Color de la pieza.
     */
    ChessPiece.Color pieceColor;
    
    /**
     * Tipo de la pieza.
     */
    ChessPiece.Type pieceType;
    
    /**
     * Si ha sido movida alguna vez.
     */
    boolean isMoved;
    /**
    * Constructor de la clase ChessPieceImplementation
    * @param colour
    * @param tipo
    */
    public ChessPieceImplementation(Chess.ChessPiece.Color colour, Chess.ChessPiece.Type tipo) {
        pieceColor = colour;
        pieceType = tipo;
        isMoved = false;
    }
    
    /**
    * Esta función devuelve el color de la pieza
    * @return pieceColor
    */
    @Override
    public ChessPiece.Color getColor() {
        return pieceColor;
    }
	
    /**
     * Esta función devuelve el tipo de la pieza
     * @return type
     */
    @Override
    public ChessPiece.Type getType() {
        return pieceType;
    }

    /**
     * Cuando el tablero mueve una ficha, llama a esta función para notificarle
     * que la ha movido.
     */
    @Override
    public void notifyMoved(){
        isMoved = true;
    }

    /**
     * Esta función devuelve si la ficha se ha movido en algún momento de la 
     * partida o no.
     * @return true if notifyMoved was called once at least, false otherwise.
     */
    @Override
    public boolean wasMoved(){
        return isMoved;
    }
	
    /**
     * Esta función devuelve un array con todas las posibles posiciones a las
     * que se puede mover una ficha.
     * @param aBoard Board containing the piece.
     * @return the array with the available positions where the piece can be moved.
     */

    @Override
    public Chess.PiecePosition[] getAvailablePositions(Chess.ChessBoard aBoard) {
        switch (getType()) {            
            case KING:
                return ChessMovementManager.getAvailablePositionsOfKing(this, aBoard);
            case BISHOP:
                return ChessMovementManager.getAvailablePositionsOfBishop(this, aBoard);
            case KNIGHT:
                return ChessMovementManager.getAvailablePositionsOfKnight(this, aBoard);
            case PAWN:
                return ChessMovementManager.getAvailablePositionsOfPawn(this, aBoard);
            case QUEEN:
                return ChessMovementManager.getAvailablePositionsOfQueen(this, aBoard);
            case ROOK:
                return ChessMovementManager.getAvailablePositionsOfRook(this, aBoard);
            default:
                return null;
        }
    }
    
    /**
     * Default method. This function tells if a piece can be moved or not.
     * @param aPosition New position of the piece.
     * @param aBoard Board containing the piece.
     * @return true if the piece can be moved, false otherwise.
     */
    
    @Override
    public boolean canMoveToPosition(Chess.PiecePosition aPosition, Chess.ChessBoard aBoard) {
        Chess.PiecePosition[] positions = getAvailablePositions(aBoard);
        if (positions != null)
            for (Chess.PiecePosition position : positions)
                if (position.equals(aPosition))
                    return true;
        return false;
    }
}