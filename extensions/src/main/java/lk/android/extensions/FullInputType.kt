package lk.android.extensions



import android.text.InputType

/**
 * A convenience object with commonly used [InputType] settings.
 * Created by jivie on 6/17/16.
 */
object FullInputType {

    /**
     * For use with proper names.
     */
    const val NAME: Int = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS or InputType.TYPE_TEXT_VARIATION_PERSON_NAME

    /**
     * For use with integers.
     */
    const val INTEGER: Int = InputType.TYPE_CLASS_NUMBER

    /**
     * For use with numbers that can have a decimal point.
     */
    const val FLOAT: Int = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL

    /**
     * For use with integers that can be negative.
     */
    const val NEGATIVE_FLOAT: Int = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED

    /**
     * For use with numbers that can have a decimal point and/or be negative.
     */
    const val NEGATIVE_INTEGER: Int = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_SIGNED

    /**
     * For phone numbers.
     */
    const val PHONE: Int = InputType.TYPE_CLASS_PHONE

    /**
     * For email addresses.
     */
    const val EMAIL: Int = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS

    /**
     * For passwords.
     */
    const val PASSWORD: Int = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

    /**
     * For full sentences that can span multiple lines.
     */
    const val SENTENCE: Int = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES or InputType.TYPE_TEXT_FLAG_MULTI_LINE

    /**
     * For full sentences that should be on only one line.
     */
    const val SINGLE_LINE_SENTENCE: Int = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES

    /**
     * For street addresses.
     */
    const val ADDRESS: Int = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS or InputType.TYPE_TEXT_FLAG_CAP_WORDS

    /**
     * For web URLs.
     */
    const val URL: Int = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_URI

    /**
     * For all-uppercase identifiers.
     */
    const val CAPS_IDENTIFIER: Int = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS
}
