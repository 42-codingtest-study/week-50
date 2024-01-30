#!/bin/bash

# 입력 파일 목록
in_files=(in1.txt in2.txt in3.txt in4.txt in5.txt)
# 대응되는 출력 파일 목록
out_files=(out1.txt out2.txt out3.txt out4.txt out5.txt)

# 입력 파일의 개수만큼 반복
for ((i = 0; i < ${#in_files[@]}; ++i)); do
    in_file="${in_files[$i]}"
    out_file="${out_files[$i]}"

    echo "Running test for $in_file..."

    # 프로그램 실행 후 결과 저장
    ./a.out < "$in_file" > temp_out.txt

    # 프로그램 출력과 정답 출력 비교 (개행 문자 무시)
    diff -b -B "$out_file" temp_out.txt &>/dev/null

    if [ $? -eq 0 ]; then
        echo "Test for $in_file Passed!"
    else
        echo "Test for $in_file Failed!"
        echo "Diff:"
        echo "$diff_result"

    fi
done
