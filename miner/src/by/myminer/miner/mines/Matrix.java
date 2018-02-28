package by.myminer.miner.mines;

/**
 *
 */
class Matrix {
  private Box[][] matrix;

  Matrix(Box defaultBox) {
    this.matrix = new Box[Ranges.getSize().x][Ranges.getSize().y];
    for (Coordinates coord : Ranges.getAllCoordinates())
      matrix[coord.x][coord.y] = defaultBox;
  }

  Box get (Coordinates coord){
    if (Ranges.inRange(coord))
      return matrix[coord.x][coord.y];
    return null;
  }

  void set (Coordinates coord,Box box){
    if (Ranges.inRange(coord))
      matrix[coord.x][coord.y] = box;
  }
}
