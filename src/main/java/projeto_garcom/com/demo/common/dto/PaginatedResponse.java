package projeto_garcom.com.demo.common.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record PaginatedResponse<T>(
        List<T> data,
        Meta meta
) {
    @Builder
    public record Meta(
            long totalItems,
            int totalPages,
            int currentPage,
            int perPage
    ) {
    }
}