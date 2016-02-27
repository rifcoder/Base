add = \x -> (\y -> x + y)

safetail xs
    | null xs = []
    | otherwise = tail xs

e9 [x,y] = (x, True)

concatMy :: [[a]] -> [a]
concatMy xss = [x | xs <- xss, x <- xs]

pairs :: [a] -> [(a, a)]
pairs xs = zip xs (tail xs)

sorted :: Ord a => [a] -> Bool
sorted xs = and [x <= y | (x, y) <- pairs xs]

qsort :: [Int] -> [Int]
qsort [] = []
qsort (x : xs) =
    qsort smaller ++ [x] ++ qsort larger
    where
        smaller = [a | a <- xs, a <= x]
        larger = [b | b <- xs, b > x]

replicateM :: Int -> a -> [a]
replicateM 0 _ = []
replicateM n x = [x] ++ replicate (n - 1) x


filterM :: (a -> Bool) -> [a] -> [a]
filterM _ [] = []
filterM p (x : xs)
    | p x = x : filterM p xs
    | otherwise = filterM p xs

mapMy :: (a -> b) -> [a] -> [b]
mapMy f [] = []
mapMy f (x : xs) = f x : mapMy f xs

allCheck :: (a -> Bool) -> [a] -> Bool
allCheck p = and . map p

