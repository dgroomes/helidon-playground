package dgroomes;

import io.helidon.webserver.http.HttpRules;
import io.helidon.webserver.http.HttpService;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;

/**
 * Roll some dice. Roll just one (the default) or roll as many as you'd like.
 */
class DiceRollService implements HttpService {

    @Override
    public void routing(HttpRules rules) {
        rules
                .get("/", (request, response) -> rollDice(response, 1))
                .get("/{dice}", this::rollDiceWithPathVariable);
    }

    /**
     * Extract a "number of dice" preference from a URL path variable, and roll that many dice.
     */
    private void rollDiceWithPathVariable(ServerRequest request,
                                          ServerResponse response) {
        String dice = request.path().pathParameters().get("dice");

        // Parse the dice parameter as an integer and handle.
        int numberOfDice;
        try {
            numberOfDice = Integer.parseInt(dice);
        } catch (NumberFormatException e) {
            response.status(400).send("The dice parameter must be an integer but found " + dice);
            return;
        }

        rollDice(response, numberOfDice);
    }

    private void rollDice(ServerResponse response, int numberOfDice) {
        int diceRollSum = 0;
        for (int i = 0; i < numberOfDice; i++) {
            diceRollSum += (int) (Math.random() * 6) + 1;
        }

        String msg = String.format("You rolled a %,d", diceRollSum);
        response.send(msg);
    }
}
