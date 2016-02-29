type Parser a = String -> [(a, String)]

returnMy :: a -> Parser a
returnMy v = \inp -> [(v, inp)]

failure :: Parser a
failure = \inp -> []

item :: Parser Char
item = \inp -> case inp of
                [] -> []
                (x : xs) -> [(x, xs)]

parse :: Parser a -> String -> [(a, String)]
parse p inp = p inp

a :: IO (Char, Char)
a = do x <- getChar
       getChar
       y <- getChar
       return (x, y)


