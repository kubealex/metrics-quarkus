# metrics-tester Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

To easily get the app up & running:

    oc new-project metrics-quarkus
    oc new-app --name metrics-quarkus --as-deployment-config=false java:openjdk-17-ubi8~https://github.com/kubealex/metrics-quarkus.git
    oc expose svc metrics-quarkus
    oc apply -f https://raw.githubusercontent.com/kubealex/metrics-quarkus/master/ocp/servicemonitor.yaml -f https://raw.githubusercontent.com/kubealex/metrics-quarkus/master/ocp/cluster-monitoring-config-map.yaml

After the build ends, you can test the endpoint via:

    for i in {1..20}; do curl -k https://$(oc get route -n test-building metrics-quarkus -o jsonpath='{.spec.host}')/run; done

The app exposes a *my-computation-timer* metric that measures the execution time of a simulated calculation in a range from 0-5000ms.

To check if metrics are exposed visit:

    echo https://$(oc get route -n openshift-console console -o jsonpath='{.spec.host})

And to see an example of average execution time in the last 5 minutes, you can try the query:

    rate(my_computation_timer_seconds_sum[5m])/rate(my_computation_timer_seconds_count[5m])

