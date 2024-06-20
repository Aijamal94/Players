import org.example.Game;
import org.example.NotRegisteredException;
import org.example.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class GameTest {
    private Game game;
    private Player player1;
    private Player player2;
    private Player player3;

    @BeforeEach
    public void setUp() {
        game = new Game();
        player1 = new Player(1, "Alice", 10);
        player2 = new Player(2, "Bob", 15);
        player3 = new Player(3, "Charlie", 10);
        game.register(player1);
        game.register(player2);
    }

    @Test
    public void testRegisterPlayer() {
        assertTrue(game.getRegisteredPlayers().contains(player1));
        assertTrue(game.getRegisteredPlayers().contains(player2));
        assertFalse(game.getRegisteredPlayers().contains(player3));
    }

    @Test
    public void testFindByName() {
        assertEquals(player1, game.findByName("Alice"));
        assertEquals(player2, game.findByName("Bob"));
        assertNull(game.findByName("Charlie"));
    }

    @Test
    public void testRoundPlayer1Wins() {
        // Alice (10) vs Bob (15) => Bob wins
        assertEquals(2, game.round("Alice", "Bob"));
    }

    @Test
    public void testRoundPlayer2Wins() {
        // Bob (15) vs Alice (10) => Bob wins
        assertEquals(1, game.round("Bob", "Alice"));
    }


    @Test
    public void testRoundDraw() {
        game.register(player3);
        // Alice (10) vs Charlie (10) => Draw
        assertEquals(0, game.round("Alice", "Charlie"));
    }

    @Test
    public void testRoundNotRegistered() {
        Exception exception = assertThrows(NotRegisteredException.class, () -> {
            game.round("Alice", "Charlie");
        });
        assertEquals("One or both players are not registered", exception.getMessage());

        exception = assertThrows(NotRegisteredException.class, () -> {
            game.round("Charlie", "Bob");
        });
        assertEquals("One or both players are not registered", exception.getMessage());

        exception = assertThrows(NotRegisteredException.class, () -> {
            game.round("Charlie", "David");
        });
        assertEquals("One or both players are not registered", exception.getMessage());
    }
}

