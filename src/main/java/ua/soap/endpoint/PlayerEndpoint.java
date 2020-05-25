package ua.soap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ua.soap.GetPlayerRequest;
import ua.soap.GetPlayerResponse;
import ua.soap.ObjectFactory;
import ua.soap.SavePlayerResponse;
import ua.soap.entity.Player;
import ua.soap.repository.PlayerRepository;

import javax.persistence.EntityNotFoundException;
import java.math.BigInteger;

@Endpoint
public class PlayerEndpoint {
    private static final String NAMESPACE_URI = "http://soap.ua";

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerEndpoint(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPlayerRequest")
    @ResponsePayload
    public GetPlayerResponse getPlayer (@RequestPayload GetPlayerRequest request) {
        ObjectFactory objectFactory = new ua.soap.ObjectFactory();
        GetPlayerResponse response = objectFactory.createGetPlayerResponse();

        final Player foundPlayer = playerRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("No entity found with the provided id"));

        ua.soap.Player player = objectFactory.createPlayer();
        player.setId(foundPlayer.getId());
        player.setLevel(BigInteger.valueOf(foundPlayer.getLevel()));
        response.setPlayer(player);
        return response;
    }

    public SavePlayerResponse savePlayer(@RequestPayload ua.soap.SavePlayerRequest request) {
        ObjectFactory objectFactory = new ObjectFactory();
        SavePlayerResponse response = objectFactory.createSavePlayerResponse();

        Player createdPlayer  = new Player(request.getId(), request.getLevel().intValue());

        response.setId(playerRepository.save(createdPlayer).getId());
        return response;
    }
}
