# cachedemo
@Cacheable and @Async not working in the same class

I stumbled into this problem somewhat surprisingly.

But the requirement is actually nothing special.
There are methods whose results should be cached and I want to call them from within the service itself.

As long as you don't need another proxy, because you don't want to use @Async in the same class, this also
works with version v2, i.e. with the "self inject" via InitializingBean.

But even this requirement to have @Async and @Cacheable in the same service is not too much of a stretch now.
Of course I don't want to use both annotations on the same method, that's for sure.

But as you can see in version v2, only the @Async calls, but not the @Cacheable calls, work externally, for example from a controller.
But internally, using the self reference, only the @Cacheable calls work, but not the @Async calls.

It looks like Spring injects into the controller one proxy and into the service (itself) the other proxy.

As you can see in version v1, without the self injection both, @Cacheable and @Async, work from the controller...

## Solutions:

 1. Do not use @Cacheable but use Caffeine or Guava Cache directly...
 2. Use Runables: https://stackoverflow.com/questions/24798695/spring-async-method-inside-a-service/40044899 ... But I don't think it's a pretty solution
 3. Split the Service... Not always possible and can also lead to less maintainable code.
 
## Suggestions?
