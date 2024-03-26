package com.euphoriav.wechat.unit;

import com.euphoriav.wechat.entity.Message;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageCreatedAtFormatterTest {

    @ParameterizedTest
    @MethodSource("createdAtExamples")
    public void getPrettyCreatedAtTest(LocalDateTime createdAt, String expectedPrettyCreatedAt) {
        Message message = new Message();
        message.setCreatedAt(createdAt);

        assertEquals(expectedPrettyCreatedAt, message.getPrettyCreatedAt());
    }

    private static Stream<Arguments> createdAtExamples() {
        return Stream.of(
                Arguments.of(LocalDateTime.of(2024, 3, 26, 4, 20, 1, 1), "26.03.2024 04:20"),
                Arguments.of(LocalDateTime.of(2023, 8, 29, 15, 30, 20, 20), "29.08.2023 15:30")
        );
    }
}
