# Averaged Perceptron Written in Scala

## Usage

```zsh
$ wget http://www.csie.ntu.edu.tw/~cjlin/libsvmtools/datasets/binary/news20.binary.bz2
$ bzip2 -d news20.binary.bz2
$ cat news20.binary | gsort -R > tmp.txt
$ head -n 15000 tmp.txt > train.txt; tail -n 4996 tmp.txt > test.txt
$ sbt -J-Xmx2G run
```
