package pl.decerto.rekrutacja.bpawlowski;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hello")
class HelloResourceV1 {

    @GetMapping("/{name}")
    ValueWrapper<String> hello(@PathVariable("name") String name) {
        return new ValueWrapper<>("Hello, %s".formatted(name));
    }

    @GetMapping("/secured/{name}")
    ValueWrapper<String> helloSecured(@PathVariable("name") String name) {
        return new ValueWrapper<>("Hello, %s".formatted(name));
    }
}
