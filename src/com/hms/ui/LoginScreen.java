package com.hms.ui;

import com.hms.models.User;
import com.hms.ui.AdministrationScreen;
import com.hms.ui.DoctorsScreen;
import com.hms.ui.PatientsScreen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginScreen extends JFrame {

    /** Card identifiers used with CardLayout.show(...). */
    public static final String CARD_SIGN_IN = "SIGN_IN";
    public static final String CARD_SIGN_UP = "SIGN_UP";
    public static final String CARD_FORGOT_PASSWORD = "FORGOT_PASSWORD";

    /** The layout manager that lets us flip between the three forms. */
    private final CardLayout cardLayout = new CardLayout();

    /** The panel that CardLayout manages - each form is added as a "card". */
    private final JPanel cardContainer = new JPanel(cardLayout);

    public LoginScreen() {
        super("City Hospital – Secure Portal Login");
        initFrame();
        initHeader();
        initCards();
        // Show the Sign In screen first by default.
        cardLayout.show(cardContainer, CARD_SIGN_IN);
    }

    /**
     * Configures the JFrame itself: size, close behaviour, minimum size
     * (so the responsive layouts never get crushed too small) and the
     * overall BorderLayout used to arrange header + card area.
     */
    private void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(UITheme.BACKGROUND);

        // A sensible starting size, but the BorderLayout/GridBagLayout
        // combination underneath means the UI reflows cleanly at any size.
        setSize(980, 680);
        setMinimumSize(new Dimension(760, 560));
        setLocationRelativeTo(null); // centre on screen
    }

    /**
     * Builds the slim header bar shown above the card area, containing a
     * simple hospital "logo" (a Unicode medical cross glyph) and title.
     * This reinforces the hospital branding on every screen (Sign In,
     * Sign Up, Forgot Password) without repeating the code three times.
     */
    private void initHeader() {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 18));
        header.setBackground(UITheme.PRIMARY);

        JLabel logo = new JLabel("⚕");  // ⚕ medical symbol
        logo.setFont(new Font("Segue UI Symbol", Font.PLAIN, 28));
        logo.setForeground(Color.WHITE);

        JLabel title = new JLabel("City Hospital Secure Portal");
        title.setFont(UITheme.FONT_LOGO);
        title.setForeground(Color.WHITE);

        header.add(logo);
        header.add(title);

        add(header, BorderLayout.NORTH);
    }

    /**
     * Instantiates each form panel and registers it with the CardLayout
     * container. Each panel receives a reference to this LoginFrame so
     * it can navigate between cards or trigger dashboard redirection.
     */
    private void initCards() {
        cardContainer.setBackground(UITheme.BACKGROUND);
        cardContainer.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        cardContainer.add(new SignInPanel(this), CARD_SIGN_IN);
        cardContainer.add(new SignUpPanel(this), CARD_SIGN_UP);
        cardContainer.add(new ForgotPasswordPanel(this), CARD_FORGOT_PASSWORD);

        add(cardContainer, BorderLayout.CENTER);
    }

    /**
     * Switches the visible card. Called by the child panels' "link"
     * buttons (e.g. "Don't have an account? Create one").
     *
     * @param cardName one of the CARD_* constants declared above.
     */
    public void showCard(String cardName) {
        cardLayout.show(cardContainer, cardName);
    }

    /**
     * Called by {@link SignInPanel} once AuthService confirms valid
     * credentials. Performs role-based redirection: closes the login
     * window and opens the dashboard matching the user's role.
     *
     * @param user the authenticated user returned by AuthService
     */
    public void handleLoginSuccess(User user) {
        JFrame dashboard = switch (user.getRole()) {
            case DOCTOR -> new DoctorsScreen(user);
            case ADMINISTRATOR -> new AdministrationScreen(user);
            default -> new PatientsScreen(user);
        };
        dashboard.setVisible(true);
        this.dispose(); // close the login window - one active window at a time
    }
}
