<!DOCTYPE html>

<head xmlns="http://www.w3.org/1999/html">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <meta http-equiv="refresh" content="10">
  <title>Web Checkers | ${title}</title>
  <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
<div class="page">

  <h1>Web Checkers | ${title}</h1>

  <!-- Provide a navigation bar -->
  <#include "nav-bar.ftl" />

  <div class="body">

    <!-- Provide a message to the user, if supplied. -->
    <#include "message.ftl" />

    <!-- TODO: future content on the Home:
            to start games,
            spectating active games,
            or replay archived games
    -->

    <#if currentUser??>
      <#if players??>
        Active Players:
        <ul>
        <#list players as player>
          <#if player != currentUser.name>
      <!--
            <form id="startGame" action="/game" method="post">
              <a href="#" onclick="event.preventDefault(); gameCenter.createGame();">${player.name}</a>
            </form>
            -->
            <a href="/">${player.name}</a>
          </#if>
        </#list>
        </ul>
      <#else>
        There are currently no other active players.
      </#if>
    <#else>
      <#if numPlayers gt 1>
        There are currently ${numPlayers} players logged in.
      <#else>
        There is currently 1 player logged in.
      </#if>
    </#if>

  </div>

</div>
</body>

</html>
