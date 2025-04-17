class Grid {
  private aliveCells = new Set<string>();

  setAliveAt(x: number, y: number) {
    this.aliveCells.add(this.toCoord(x, y));
  }
  isAliveAt(x: number, y: number): boolean {
    return this.aliveCells.has(this.toCoord(x, y));
  }
  toCoord(x: number, y: number): string {
    return `${x};${y}`;
  }
  countNeighbors(col: number, line: number): number {
    let result = 0;
    if (this.isAliveAt(col - 1, line - 1)) ++result;
    if (this.isAliveAt(col - 1, line)) ++result;
    if (this.isAliveAt(col - 1, line + 1)) ++result;
    if (this.isAliveAt(col, line - 1)) ++result;
    if (this.isAliveAt(col, line + 1)) ++result;
    if (this.isAliveAt(col + 1, line - 1)) ++result;
    if (this.isAliveAt(col + 1, line)) ++result;
    if (this.isAliveAt(col + 1, line + 1)) ++result;

    return result;
  }
}

export class GameOfLifeBoard {
  private generation: number;
  private nbLines: number;
  private nbColumns: number;
  private grid: Grid;

  constructor(private boardAsStr: string) {
    const boardLines = boardAsStr.split(`\n`);
    this.generation = parseInt(boardLines[0].split(" ")[1].split(":")[0]);
    const size = boardLines[1].split(" ");
    this.nbLines = parseInt(size[0]);
    this.nbColumns = parseInt(size[1]);

    this.grid = new Grid();
    for (let line = 0; line < this.nbLines; ++line) {
      for (let col = 0; col < this.nbColumns; ++col) {
        if (boardLines[line + 2][col] === "*") {
          this.grid.setAliveAt(col, line);
        }
      }
    }
  }

  nextTurn(): void {
    const newGrid = new Grid();
    for (let line = 0; line < this.nbLines; ++line) {
      for (let col = 0; col < this.nbColumns; ++col) {
        const neighbors = this.grid.countNeighbors(col, line);
        if (
          (this.grid.isAliveAt(col, line) && neighbors === 2) ||
          neighbors === 3
        ) {
          newGrid.setAliveAt(col, line);
        }
      }
    }
    this.grid = newGrid;
    ++this.generation;
  }

  toString(): string {
    const header = `Generation ${this.generation}:\n${this.nbLines} ${this.nbColumns}`;

    let cells = "";
    for (let i = 0; i < this.nbLines; ++i) {
      cells += `\n`;
      for (let j = 0; j < this.nbColumns; ++j) {
        cells += this.grid.isAliveAt(j, i) ? "*" : ".";
      }
    }

    return header + cells;
  }
}
