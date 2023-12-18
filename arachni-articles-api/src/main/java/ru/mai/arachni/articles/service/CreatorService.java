package ru.mai.arachni.articles.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import ru.mai.arachni.articles.dto.request.PaginationRequest;
import ru.mai.arachni.articles.dto.response.PaginationResponse;
import ru.mai.arachni.articles.core.domain.Creator;
import ru.mai.arachni.articles.core.repository.CreatorRepository;
import ru.mai.arachni.articles.core.repository.pagerequest.OffsetBasedPageRequest;

import java.util.List;

@RequiredArgsConstructor
public class CreatorService {
    private final CreatorRepository creatorRepository;

    public PaginationResponse<String> getCreatorList(PaginationRequest paginationRequest) {
        Page<Creator> creatorPage = creatorRepository.findByCreatorContainingIgnoreCase(
                paginationRequest.getSearchString(),
                new OffsetBasedPageRequest(
                        paginationRequest.getSkip(),
                        paginationRequest.getLimit(),
                        Sort.by(
                                paginationRequest.getOrder(),
                                "creator"
                        )
                )
        );

        List<String> creatorResponses = creatorPage
                .getContent()
                .stream()
                .map(Creator::getCreator)
                .toList();

        return new PaginationResponse<>(
                creatorResponses,
                creatorPage.getTotalElements()
        );
    }
}
