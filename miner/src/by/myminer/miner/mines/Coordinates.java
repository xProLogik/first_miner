package by.myminer.miner.mines;

/**
 *
 */
public class Coordinates {
  public final int x;
  public final int y;

  public Coordinates(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Coordinates that = (Coordinates) o;

    if (x != that.x) return false;
    return y == that.y;
  }


}
