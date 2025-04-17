import { GameOfLifeBoard } from "./game-of-life";

describe("Game of life", () => {
  it("should return an empty grid to an empty grid if no turn is changed", () => {
    const empty = ["Generation 1:", "0 0"].join("\n");
    const expectedOuput = empty;

    const board = new GameOfLifeBoard(empty);
    checkBoardMatches(board.toString(), expectedOuput);
  });

  it("should increase generation when calling nextTurn", () => {
    const empty = ["Generation 1:", "0 0"].join("\n");
    const board = new GameOfLifeBoard(empty);

    board.nextTurn();

    const expected = ["Generation 2:", "0 0"].join("\n");
    checkBoardMatches(board.toString(), expected);
  });

  it("should return grid with empty cells for an input grid with empty cells", () => {
    const input = ["Generation 1:", "2 3", "...", "..."].join("\n");

    const board = new GameOfLifeBoard(input);

    board.nextTurn();

    const expected = ["Generation 2:", "2 3", "...", "..."].join("\n");
    checkBoardMatches(board.toString(), expected);
  });

  it("should kill any cell that has strictly less than 2 neighbors", () => {
    const input = ["Generation 1:", "2 4", "**..", "...*"].join("\n");

    const board = new GameOfLifeBoard(input);

    board.nextTurn();

    const expected = ["Generation 2:", "2 4", "..??", "???."].join("\n");
    checkBoardMatches(board.toString(), expected);
  });

  it("should keep alive any cell that has 2 or 3 neighbours", () => {
    const input = ["Generation 1:", "2 4", "***.", "*..."].join("\n");

    const board = new GameOfLifeBoard(input);

    board.nextTurn();

    const expected = ["Generation 2:", "2 4", "**..", "*..."].join("\n");
    checkBoardMatches(board.toString(), expected);
  });

  it("should kill any cell that has 4 or more neighbours", () => {
    const input = ["Generation 1:", "2 4", "***.", "****"].join("\n");

    const board = new GameOfLifeBoard(input);

    board.nextTurn();

    const expected = ["Generation 2:", "2 4", "?..?", "????"].join("\n");
    checkBoardMatches(board.toString(), expected);
  });

  it("should spawn any dead cell that has exactly 3 neighbours", () => {
    const input = ["Generation 1:", "2 4", "***.", "...."].join("\n");

    const board = new GameOfLifeBoard(input);

    board.nextTurn();

    const expected = ["Generation 2:", "2 4", "????", "?*??"].join("\n");
    checkBoardMatches(board.toString(), expected);
  });
});

function checkBoardMatches(actual: string, expected: string) {
  const actualLines = actual.split("\n");
  const expectedLines = expected.split("\n");

  const errors: string[] = [];

  if (actualLines[0] !== expectedLines[0]) {
    errors.push(
      `Generation line do not match. Expected ${expectedLines[0]}, Got : ${actualLines[0]}`
    );
  }
  if (actualLines[1] !== expectedLines[1]) {
    errors.push(
      `Grid size do not match. Expected ${expectedLines[1]}, Got : ${actualLines[1]}`
    );
  }

  for (let i = 2; i < actualLines.length; ++i) {
    const actualCellLine = actualLines[i];
    const expectedCellLine = expectedLines[i];

    for (let index = 0; index < expectedCellLine.length; ++index) {
      if (expectedCellLine[index] === "?") {
        continue;
      }
      if (expectedCellLine[index] !== actualCellLine[index]) {
        errors.push(
          `Error at line ${i - 1}, column ${index + 1}. Expected: ${expectedCellLine[index]}, Got: ${actualCellLine[index]}`
        );
      }
    }
  }
  expect(errors).toEqual([]);
}
