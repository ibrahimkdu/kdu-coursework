document.addEventListener("DOMContentLoaded", async function () {
  await fetchLoggedInUsername();
});

async function fetchLoggedInUsername() {
  try {
    const response = await fetch("/api/loggedInUser");
    if (response.ok) {
      const data = await response.json();
      const { username } = data;
      if (username) {
        updateUsernameInTweetSection(username);
      }
    } else {
      throw new Error("Failed to fetch logged-in user's username");
    }
  } catch (error) {
    console.error("Error fetching logged-in user's username:", error);
    // Handle error
  }
}

function updateUsernameInTweetSection(username) {
  const usernameElements = document.querySelectorAll(
    ".navname, .navuser, .name"
  );
  usernameElements.forEach((element) => {
    element.textContent = username;
  });
}
document.addEventListener("DOMContentLoaded", function () {
  document.querySelectorAll(".post-input").forEach((input) => {
    input.addEventListener("input", function () {
      const tweetBtn =
        this.parentElement.parentElement.querySelector(".tweet-btn");
      if (this.value.trim() !== "") {
        // If input is not empty, enable the post button
        tweetBtn.removeAttribute("disabled");
      } else {
        // If input is empty, disable the post button
        tweetBtn.setAttribute("disabled", "disabled");
      }
    });
  });

  const TweetButton = document.querySelector(".tweet-btn");
  const PostInput = document.querySelector(".post-input");
  const PostsContainer = document.querySelector(".posts");

  // Function to fetch all posts from the API
  async function fetchAllPosts() {
    try {
      const response = await fetch("/api/posts");
      if (response.ok) {
        const posts = await response.json();
        if (posts.length > 0) {
          posts.forEach((post) => renderPost(post));
        } else {
          // Handle case where no posts are available
          const noPostsMessage = document.createElement("div");
          noPostsMessage.textContent = "No posts available";
          PostsContainer.appendChild(noPostsMessage);
        }
      } else {
        throw new Error("Failed to fetch posts");
      }
    } catch (error) {
      console.error("Error fetching posts:", error);
      alert("Failed to fetch posts. Please try again.");
    }
  }

  // Function to render a single post
  function renderPost(post) {
    const newPost = document.createElement("div");
    newPost.classList.add("newpost");
    newPost.innerHTML = `
            <div class="postinfo">
                <div class="profileimage">
                    <img src="profiless.png" alt="" />
                </div>
                <div class="postdata">
                    <div class="userinfo">
                        <div class="name">${post.username}</div>
                        <div class="time">@nit_hck - 1s</div>
                        <div class="moreinfo">
                            <img src="moreinfo.png" alt="" />
                        </div>
                    </div>
                    <div class="postcontent">
                        <div class="line1">${post.content}</div>
                    </div>
                </div>
            </div>
            <div class="post-icons">
                <div class="postingicons">
                    <img src="comment.png" alt="" />
                </div>
                <div class="postingicons">
                    <img src="repost.png" alt="" />
                </div>
                <div class="postingicons">
                    <button class="like-post"><img src="like.png" alt="like" /></button>
                    <button class="unlike-post" style="display: none;"><img src="unlike.png" alt="unlike" /></button>
                    <span class="likes-count"></span>
                </div>
                <div class="postingicons">
                    <img src="insight.png" alt="" />
                </div>
                <div class="postingicons">
                    <img src="save.png" alt="" />
                </div>
                <div class="postingicons">
                    <img src="export.png" alt="" />
                </div>
            </div>
        `;
    PostsContainer.appendChild(newPost);
  }
  TweetButton.addEventListener("click", function (event) {
    event.preventDefault(); // Prevent default form submission behavior

    const PostInput = document.querySelector(".post-input");
    const PostContent = PostInput.value.trim();

    if (PostContent !== "") {
      try {
        // Get the username from the .name element's text content
        const usernameElement = document.querySelector(".name");
        const username = usernameElement.textContent.trim();

        // Send a POST request to create a new post with the retrieved username and post content
        fetch("/api/posts", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({ username: username, content: PostContent }),
        })
          .then((response) => {
            if (response.ok) {
              // Clear the input field
              PostInput.value = "";

              // Fetch all posts again to update the client-side view
              return fetchAllPosts();
            } else {
              throw new Error("Failed to create post");
            }
          })
          .catch((error) => {
            console.error("Error creating post:", error);
            alert("Failed to create post. Please try again.");
          });
      } catch (error) {
        console.error("Error creating post:", error);
        alert("Failed to create post. Please try again.");
      }
    }
  });

  document.addEventListener("click", function (event) {
    if (event.target.closest(".like-post img")) {
      const post = event.target.closest(".newpost");
      const likesCount = post.querySelector(".likes-count");
      const likeButton = post.querySelector(".like-post");
      const unlikeButton = post.querySelector(".unlike-post");

      let currentLikes = parseInt(likesCount.textContent) || 0;
      if (likeButton.querySelector("img").getAttribute("src") === "like.png") {
        likeButton.querySelector("img").setAttribute("src", "unlike.png");
        currentLikes++;
      } else {
        likeButton.querySelector("img").setAttribute("src", "like.png");
        currentLikes--;
      }
      likesCount.textContent = currentLikes > 0 ? currentLikes : "";
    }
  });
});
document.addEventListener("DOMContentLoaded", function () {
  const floatingButton = document.querySelector(".floating-tweet-box-icon");
  const profileImage = document.querySelector(".topnavbar img");
  const mobileNavigation = document.querySelector(".navigationmobile");
  const tweetBox = document.querySelector(".tweet-box");
  const tweetSection = document.querySelector(".tweetsection");

  let isTweetSectionVisible = false;

  floatingButton.addEventListener("click", function () {
    if (isTweetSectionVisible) {
      tweetSection.style.display = "none";
    } else {
      tweetSection.style.display = "flex";
    }
    isTweetSectionVisible = !isTweetSectionVisible;
  });
  let isTweetBoxVisible = false;
  let isMobileNavigationVisible = false;

  floatingButton.addEventListener("click", function () {
    if (isTweetBoxVisible) {
      tweetBox.style.display = "none";
    } else {
      tweetBox.style.display = "flex";
    }
    isTweetBoxVisible = !isTweetBoxVisible;
  });

  profileImage.addEventListener("click", function () {
    if (isMobileNavigationVisible) {
      mobileNavigation.style.display = "none"; // Hide mobile navigation if already visible
    } else {
      mobileNavigation.style.display = "flex"; // Show mobile navigation if not visible
    }
    isMobileNavigationVisible = !isMobileNavigationVisible; // Toggle visibility flag
  });
});
