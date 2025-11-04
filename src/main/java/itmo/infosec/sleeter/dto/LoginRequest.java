package itmo.infosec.sleeter.dto;

public record LoginRequest(
        String name,
        String password
) {
}
