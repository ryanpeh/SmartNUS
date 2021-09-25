package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.choice.Choice;
import seedu.address.model.question.Address;
import seedu.address.model.question.Email;
import seedu.address.model.question.Name;
import seedu.address.model.question.Question;
import seedu.address.model.question.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Question objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final Choice DEFAULT_CHOICE = new Choice("option 1", true);

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private Set<Choice> choices;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        choices = new HashSet<>();
        choices.add(DEFAULT_CHOICE);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code questionToCopy}.
     */
    public PersonBuilder(Question questionToCopy) {
        name = questionToCopy.getName();
        phone = questionToCopy.getPhone();
        email = questionToCopy.getEmail();
        address = questionToCopy.getAddress();
        tags = new HashSet<>(questionToCopy.getTags());
        choices = new HashSet<>(questionToCopy.getChoices());
    }

    /**
     * Sets the {@code Name} of the {@code Question} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Question} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Question} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Question} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Question} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Question build() {
        return new Question(name, phone, email, address, tags, choices);
    }

}
