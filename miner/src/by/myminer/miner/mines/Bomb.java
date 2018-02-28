package by.myminer.miner.mines;

/**
 *
 */
class Bomb {
  private Matrix bombMap;
  private int totalBomb;

  Bomb(int totalBomb) {
    this.totalBomb = totalBomb;
    fixCountBomb();
  }

  private void fixCountBomb() {
    int maxBomb = Ranges.getSize().x * Ranges.getSize().y * 2 / 3;
    if (totalBomb > maxBomb)
      totalBomb = maxBomb;
  }

  void start() {
    bombMap = new Matrix(Box.ZERO);
    for (int i = 0; i < totalBomb; i++) {
      placeBomb();
    }
  }

  Box get(Coordinates coord) {
    return bombMap.get(coord);
  }

  private void placeBomb() {
    while (true) {
      Coordinates coord = Ranges.getRandomCord();
      if (Box.BOMB == bombMap.get(coord))
        continue;
      bombMap.set(coord, Box.BOMB);
      incNumbersAroundBomb(coord);
      break;
    }
  }

  private void incNumbersAroundBomb(Coordinates coord) {
    for (Coordinates around : Ranges.getCoordAround(coord))
      if (Box.BOMB != bombMap.get(around))
        bombMap.set(around, bombMap.get(around).nextNumberBox());
  }

  public int getTotalBomb() {
    return totalBomb;
  }
}
