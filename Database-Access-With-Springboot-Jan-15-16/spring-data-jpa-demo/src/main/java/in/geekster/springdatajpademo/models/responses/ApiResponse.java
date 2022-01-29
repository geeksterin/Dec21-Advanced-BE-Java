package in.geekster.springdatajpademo.models.responses;

import in.geekster.springdatajpademo.enums.RequestStatus;
import lombok.Data;

@Data
public class ApiResponse<D> {
    private RequestStatus status;
    private D data;
    private Error error;

    public static <D> ApiResponse<D> success(final D data) {
        final ApiResponse<D> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(RequestStatus.SUCCESS);
        apiResponse.setError(null);
        apiResponse.setData(data);
        return apiResponse;
    }

    public static <D> ApiResponse<D> error(final Error error) {
        final ApiResponse<D> apiResponse = new ApiResponse<>();
        apiResponse.setStatus(RequestStatus.FAILURE);
        apiResponse.setData(null);
        apiResponse.setError(error);
        return apiResponse;
    }
}
