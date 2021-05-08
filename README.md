# Java-HCX-Cipher
We’ve creates a new cipher that include 3 sub ciphers which are Hill cipher, Caesar cipher, and XOR cipher, we named this cipher as HCX Cipher.

We've chosen three ciphers in one cipher to improve our security and to make it unbreakable by being very resistant to brute force attacks and to be the perfect cipher with one-time pad.
In our algorithm, we've 3 input which are plaintext, XOR key and shifting key, in addition to the previous keys, we have a fixed key for the HILL cipher which we need for the matrix and its inverse fixed key at the decryption side.
Our keys entered randomly by the user, the XOR key will be letters only, And the Shifting key will be any range number from 1 till 26 because we are dealing with chars and ascii table, the last key is fixed size 2*2  key [H][I][L][L], 
which we will use it with Hill matrix cipher, the three keys of our cipher uses the same key for both encryption and decryption. That is why it is known as a symmetric encryption
