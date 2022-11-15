echo "Create S3 Bucket"

aws --endpoint-url=http://127.0.0.1:4566 s3api create-bucket --bucket balancefy-d

echo "Bukets cretated:"

awslocal s3 ls

echo "Setting the buckets above to public read"

aws --endpoint-url=http://127.0.0.1:4566 s3api put-bucket-acl --bucket balancefy-d --acl public-read-write