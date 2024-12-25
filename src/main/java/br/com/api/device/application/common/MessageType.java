package br.com.api.device.application.common;

public enum MessageType {
    DEVICE_NOT_FOUND("device.not.found"),
    DEVICE_FOUND("device.found"),
    DEVICE_ADDED_SUCCESS("device.added.success"),
    DEVICE_UPDATED_SUCCESS("device.updated.success"),
    DEVICE_DELETED_SUCCESS("device.deleted.success"),
    DEVICE_ERROR("device.error");

    private final String code;

    MessageType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
