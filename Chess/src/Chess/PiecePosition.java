package Chess;

public class PiecePosition {

	/**
	 * Funcion que comprueba si la posicion esta entre los limites del
         * tablero
         * @param column
         * @param row
         * @return cierto o falso si la columna y la fila esta en los limites del
         * tablero
	 */
	public static boolean isAvailable(int column, int row) {
		return column >= 0 && column < 8 && row >= 0 && row < 8;
	}

	/**
	 * Funcion que comprueba si una pieza puede moverse a una posicion dada
         * @param position
         * @param columnIncrement
         * @param rowIncrement
         * @return cierto si puede y falso si no a traves de la funcion 
         * isAvailable(column,row)
	 */
	static boolean isAvailable(PiecePosition position, int columnIncrement, int rowIncrement) {
		if (position == null)
			return false;
		
		int newColumn = position.getColumn() + columnIncrement;
		int newRow = position.getRow() + rowIncrement;
		return isAvailable(newColumn, newRow);
	}

	/**
	 * Funcion que comprueba si una pieza esta en una posicion valida.
         * @param position
         * @return cierto o falso
	 */
	static boolean isAvailable(PiecePosition position) {
		if (position == null)
			return false;
		return isAvailable(position.getColumn(), position.getRow());
	}
        /**
         * Variables enteras para la columna y la fila
         */
	private int column, row;

	/**
	 * Constructor de la clase PiecePosition en la que asignamos valor a
         * column y row.
         * @param column
         * @param row
	 */
	public PiecePosition(int column, int row) {
		this.column = column;
		this.row = row;
	}
	
	/**
	 * Funcion que devuelve el valor de column
         * @return column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Funcion que devuelve el valor de row
         * @return 
	 */
	public int getRow() {
		return row;
	}
        
	/**
         * Funcion que asigna los valores de columna y fila despues de comprobar
         * que es una posicion valida.
         * @param column
         * @param row
         * @return cierto si ha podido asignar valores o falso si no es valido.
         */
	public boolean setValues(int column, int row) {
		if (isAvailable(column, row)) {
			this.column = column;
			this.row = row;			
			return true;
		}
		return false;
	}
	
	/**
	 * Funcion que comprueba si la posicion a la que se va a mover esta libre
         * Si esta libre crea una nueva pieza y si esta ocupada devuelve nulo.
         * @param columnCount
         * @param rowCount
         * @return null o la pieza
	 */
	public PiecePosition getDisplacedPiece(int columnCount, int rowCount) {		
		if (!isAvailable(this, columnCount, rowCount))
			return null;
		int newColumn = getColumn() + columnCount;
		int newRow = getRow() + rowCount;
		return new PiecePosition(newColumn, newRow);
	}
	
	/**
	 * Funcion que crea una nueva pieza en el mismo lugar que la anterior.
         * @return nueva pieza en el mismo sitio
	 */
        @Override
	public PiecePosition clone() {
		return new PiecePosition(column, row);
	}
	
	/**
	 * Funcion que comprueba si 2 piezas estan en la misma posicion.
         * @param aPosition
         * @return cierto o falso
	 */
	public boolean equals(PiecePosition aPosition) {
		return aPosition.getColumn() == getColumn() && aPosition.getRow() == getRow();
	}
}
