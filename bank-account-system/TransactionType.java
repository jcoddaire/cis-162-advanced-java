package bank;

/**
 * This class is used to determine the type of object.
 * It also works with deserializing files.
 * 
 * Jacob Coddaire
 * CIS 163
 */
public enum TransactionType 
{
		DEPOSIT,
		WITHDRAW,
		CHARGE,
		PAYMENT,
		OVERDRAFT_FEE,
		INTEREST
}