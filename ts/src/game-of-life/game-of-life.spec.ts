export class GameOfLifeBoard {
  private generation: number;
  private nbLines: number;
  private nbColumns: number;

  constructor(private boardAsStr: string) {
    const boardLines = boardAsStr.split(`\n`);
    this.generation = parseInt(boardLines[0].split(" ")[1].split(":")[0]);
    const size = boardLines[1].split(" ");
    this.nbLines = parseInt(size[0]);
    this.nbColumns = parseInt(size[1]);
  }

  nextTurn(): void {
    ++this.generation;
  }

  toString(): string {
    const header = `Generation ${this.generation}:\n${this.nbLines} ${this.nbColumns}`;

    let cells = "";
    for (let i = 0; i < this.nbLines; ++i) {
      cells += `\n`;
      for (let j = 0; j < this.nbColumns; ++j) {
        cells += ".";
      }
    }

    return header + cells;
  }
}

describe("Game of life", () => {
  it("should return an empty grid to an empty grid if no turn is changed", () => {
    const empty = ["Generation 1:", "0 0"].join("\n");
    const expectedOuput = empty;

    const board = new GameOfLifeBoard(empty);
    expect(board.toString()).toEqual(expectedOuput);
  });

  it("should increase generation when calling nextTurn", () => {
    const empty = ["Generation 1:", "0 0"].join("\n");
    const board = new GameOfLifeBoard(empty);

    board.nextTurn();

    const expected = ["Generation 2:", "0 0"].join("\n");
    expect(board.toString()).toEqual(expected);
  });

  it("should return grid with empty cells for an input grid with empty cells", () => {
    const input = ["Generation 1:", "2 3", "...", "..."].join("\n");

    const board = new GameOfLifeBoard(input);

    board.nextTurn();

    const expected = ["Generation 2:", "2 3", "...", "..."].join("\n");
    expect(board.toString()).toEqual(expected);
  });
});
