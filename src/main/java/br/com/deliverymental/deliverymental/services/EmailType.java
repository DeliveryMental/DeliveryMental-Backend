package br.com.deliverymental.deliverymental.services;

public enum EmailType {
    CreateAccount("src/main/resources/assets/newAccount.html", "Seja bem-vindo(a) ao DeliveryMental!"),
    UpdateAccount("", ""),
    ScheduledTime("", "");

    private final String filePath;
    private final String title;

    EmailType(String filePath, String title) {
        this.filePath = filePath;
        this.title = title;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getTitle() {
        return title;
    }
}
