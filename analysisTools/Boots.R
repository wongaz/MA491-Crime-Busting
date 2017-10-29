#MA382 Fall 2016 Nov 3, 2016

#Using bootstrap in R

#the package we'll need to perform bootstrapping
library(boot)

#importing and viewing a csv file
sudoku1 <- read.csv(file="s12c_difficulties.csv")
View(sudoku)
sudoku2 <- read.csv(file="saddistic.csv")
View(sudoku)
#focusing on the "Yds" column (remove last row which is career numbers)
y1<-sudoku1$DHM[1:50]
y2 <-sudoku2$HDF[1:50]
#seeing if the data is plausibly normal
qqnorm(y1,datax=T,ylab="HDM",pch=16, main = "Q-Q Plot for Hard Puzzle")
qqnorm(y2,datax=T,ylab="HDM",pch=16, main ="Q-Q Plot for AI Escargot")

var(y2)


#function to return the mean that we'll need when we call the boot() function
samplemean <- function(x, d) {
  return(mean(x[d]))
}

#10,000 bootstrap samples
boot_y1_mean <- boot(y1,samplemean,10000)
boot_y1_mean
boot_y2_mean <- boot(y2,samplemean,10000)
boot_y2_mean
var(boot_y2_mean$t)
#this will generate a histogram of samples, and a normal qq plot
plot(boot_y1_mean)
plot(boot_y2_mean)

#in order to obtain sample quantiles and p-values, extract the column t
#that contains the values of the mean of the replicates
mean_values <- boot_y1_mean$t
quantile(mean_values,0.05)

