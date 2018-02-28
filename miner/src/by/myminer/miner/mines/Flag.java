package by.myminer.miner.mines;

/**
 *
 */
class Flag {
  private Matrix flagMap;
  private int countClosedBoxes;

  void start() {
    flagMap = new Matrix(Box.CLOSED);
    countClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;
  }

  Box get(Coordinates coord) {
    return flagMap.get(coord);
  }

  void setOpenedToBox(Coordinates coord) {
    flagMap.set(coord, Box.OPENED);
    countClosedBoxes--;
  }


  void toggleFlagedToBox(Coordinates coord) {
    switch (flagMap.get(coord)) {
      case FLAGED:
        setClosedToBox(coord);
        break;
      case CLOSED:
        setFlagedToBox(coord);
        break;
    }
  }

  private void setClosedToBox(Coordinates coord) {
    flagMap.set(coord, Box.CLOSED);
  }

  private void setFlagedToBox(Coordinates coord) {
    flagMap.set(coord, Box.FLAGED);
  }

  int getCountClosedBoxes() {
    return countClosedBoxes;
  }

  void setBombedToBox(Coordinates coord) {
    flagMap.set(coord, Box.BOMBED);
  }

  void setOpenedToClosedBombBox(Coordinates coord) {
    if (flagMap.get(coord) == Box.CLOSED)
      flagMap.set(coord, Box.OPENED);
  }

  void setNobombToFlagedSafeBox(Coordinates coord) {
    if (flagMap.get(coord) == Box.FLAGED)
      flagMap.set(coord, Box.NOBOMB);
  }

  int getCountOfFlagedBoxesAround(Coordinates coord) {
    int count = 0;
    for (Coordinates around : Ranges.getCoordAround(coord))
      if (flagMap.get(around)== Box.FLAGED)
        count++;
    return count;
  }
}
