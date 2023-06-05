# customIrc2Token Token Documentation

This token implementation inherits from the [`javaee-tokens` `IRC2Mintable`](https://github.com/sink772/javaee-tokens/blob/022166ffbfe6eb76103c2a5d3b5325fba926efb7/tokens/src/main/java/com/iconloop/score/token/irc2/IRC2Mintable.java).

---

## `customIrc2Token::constructor`

### 📜 Method Call

- Default contract constructor.
- Access: Everyone

```java
public customIrc2Token (String _name, String _symbol, int _decimals)
```

- `_name`: The token name
- `_symbol`: The token symbol
- `_decimals`: The token decimals

## `customIrc2Token::mintToMany`

### 📜 Method Call

- Mint new tokens to multiple addresses at once
- Access: IRC2Mintable::minter

```java
@External
public void mintToMany (
  Address[] addresses, 
  BigInteger[] amounts
)
```

- `addresses`: An array of addresses to send tokens to
- `amounts`: An array of token amounts to mint

### 🧪 Example call

```java
{
  "to": customIrc2Token,
  "method": "mintToMany",
  "params": {
    "addresses": [
      "hx000000000000000000000000000000000000000a", 
      "hx000000000000000000000000000000000000000b", 
      "hx000000000000000000000000000000000000000c"
    ],
    "amounts": [
      "0x1",
      "0x2",
      "0x3"
    ]
  }
}
```
