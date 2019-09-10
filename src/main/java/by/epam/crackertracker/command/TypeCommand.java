/**
 * enam to choose command by accepted parameter
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import by.epam.crackertracker.service.*;

public enum TypeCommand {
    LOGIN(new LoginCommand(new UserService(), new AdviceService(), new SubscriptionService(), new ProgramService())),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationCommand(new UserService())),
    LANGUAGE(new LanguageCommand()),
    SELECT_PRODUCT(new SelectProductByCaloriesCommand(new ProductService())),
    SELECT_USER(new SelectUserCommand(new UserService())),
    INPUT_MESSAGE(new CheckInputMessageCommand(new MessageService())),
    OUTPUT_MESSAGE(new CheckOutputMessageCommand(new MessageService())),
    SELECT_ALL_PRODUCTS(new SelectAllProductsCommand(new ProductService())),
    UPDATE_PROFILE(new UpdateProfileCommand(new UserService())),
    CHANGE_PASS(new UpdatePasswordCommand(new UserService())),
    SELECT_ADVICE(new SelectAllAdviceCommand(new AdviceService())),
    ADD_ADVICE(new AddAdviceCommand(new AdviceService())),
    DELETE_ADVICE(new DeleteAdviceCommand(new AdviceService())),
    SELECT_USER_STATUS(new SelectUserByRoleCommand(new UserService())),
    SELECT_USER_GENDER(new SelectUserByGenderCommand(new UserService())),
    DELETE_USER(new DeleteUserCommand(new UserService())),
    UPDATE_PRODUCT(new UpdateProductCommand(new ProductService())),
    DELETE_PRODUCT(new DeleteProductCommand(new ProductService())),
    ADD_PRODUCT(new AddProductCommand(new ProductService())),
    SHOW_REVIEW(new ShowReviewCommand(new ReviewService())),
    DELETE_REVIEW(new DeleteReviewCommand(new ReviewService())),
    SEND_MESSAGE(new SendMessageCommand(new MessageService())),
    CHANGE_ROLE_USER(new ChangeRoleUserCommand(new UserService())),
    DEPOSIT(new DepositCommand(new UserService())),
    SELECT_CURATOR_USERS(new SelectCuratorUsersCommand(new SubscriptionService())),
    SELECT_ALL_SUBSCRIPTION(new SelectAllSubscriptionsCommand(new SubscriptionService())),
    SEND_REVIEW(new SendReviewCommand(new ReviewService())),
    LOAD_REVIEW(new LoadReviewCommand(new ReviewService())),
    DELETE_MESSAGE(new DeleteMessageCommand(new MessageService())),
    SEARCH_PRODUCT(new SearchProductCommand(new ProductService())),
    SELECT_ALL_CURATOR_SUBS(new SelectAllCuratorSubscriptionCommand(new SubscriptionService())),
    DELETE_SUBSCRIBE(new DeleteSubscribeCommand(new SubscriptionService())),
    SELECT_ALL_PROGRAMS_NAME(new SelectAllProgramsNameCommand(new ProgramsNameService())),
    DELETE_PROGRAM_NAME(new DeleteProgramNameCommand(new ProgramsNameService())),
    SELECT_CURATOR_PROGRAMS(new SelectCuratorProgramCommand(new ProgramsNameService())),
    CALCULATE(new CalculatorCommand()),
    CALCULATE_CALORIES(new CalculatorCaloriesCommand()),
    ADD_PROGRAM_NAME(new AddProgramNameCommand(new ProgramsNameService())),
    ADD_PRODUCT_TO_PROGRAM(new AddProductToProgramCommand(new ProgramService())),
    UPDATE_PROGRAM(new UpdateProgramCommand(new ProgramsNameService())),
    EDIT_PROGRAM_NAME(new EditProgramNameCommand()),
    EDIT_COMPONENT_PROGRAM(new EditComponentProgramCommand(new ProgramService())),
    DELETE_PRODUCT_PROGRAM(new DeleteProgramProductCommand(new ProgramService())),
    USER_MESSAGE(new UserMessageCommand()),
    BUY_PROGRAM(new BuyProgramCommand(new SubscriptionService(), new UserService())),
    SHOW_HISTORY_SUBSCRIPTION(new ShowHistorySubscriptionCommand(new SubscriptionService())),
    ADD_PRODUCT_BUCKET(new AddProductBucketCommand(new BucketService())),
    DELETE_PRODUCT_BUCKET(new DeleteProductBucketCommand(new BucketService())),
    DELETE_PRODUCT_ID_BUCKET(new DeleteProductIdBucketCommand(new BucketService())),
    CALCULATE_CALORIES_BUCKET(new CalculatorCaloriesBucketCommand());

    private Command command;

    private TypeCommand(Command command){
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
